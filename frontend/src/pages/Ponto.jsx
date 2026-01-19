import React, { useState, useEffect } from "react";
import { Clock, LogOut } from "lucide-react";
import { useAuth } from "../contexts/AuthContext";
import api from "../api/api";
import { Card } from "../components/card";
import { Button } from "../components/button";

export default function Ponto() {
  const { user, logout } = useAuth();

  const [status, setStatus] = useState("idle");
  const [record, setRecord] = useState(null);
  const [loading, setLoading] = useState(false);
  const [currentTime, setCurrentTime] = useState(new Date());

  // Atualiza o relógio a cada segundo
  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  // Verifica se existe ponto aberto ao carregar a página
  useEffect(() => {
    if (user) {
      checkOpenRecord();
    }
  }, [user]);

  const checkOpenRecord = async () => {
    try {
      const response = await api.get("/work/list", {
        params: {
          page: 0,
          size: 1,
        },
      });

      if (response.data && response.data.content) {
        const records = response.data.content;

        if (records.length > 0) {
          const lastRecord = records[0];

          if (
            lastRecord.employee?.id === user.id &&
            !lastRecord.checkoutTime
          ) {
            setRecord(lastRecord);
            setStatus("active");
          }
        }
      }
    } catch {
      // Silencioso: assume que não há ponto aberto
    }
  };

  const handleAction = async () => {
    if (!user) return;

    setLoading(true);

    const endpoint = status === "idle" ? "/work/checkin" : "/work/checkout";

    try {
      const response = await api.post(
        `${endpoint}?employeeId=${user.id}`
      );

      setRecord(response.data);
      setStatus(status === "idle" ? "active" : "finished");
    } catch {
      const now = new Date();

      if (status === "idle") {
        setRecord({
          id: Math.random(),
          checkinTime: now.toISOString(),
          checkoutTime: null,
          duration: null,
        });
        setStatus("active");
      } else {
        const checkinDate = new Date(record.checkinTime);
        const diffMs = now - checkinDate;
        const diffMinutes = Math.floor(diffMs / 1000 / 60);

        setRecord({
          ...record,
          checkoutTime: now.toISOString(),
          duration: diffMinutes,
        });
        setStatus("finished");
      }
    } finally {
      setLoading(false);
    }
  };

  // Calcula duração em tempo real
  const calculateDuration = () => {
    if (!record?.checkinTime) {
      return { hours: 0, minutes: 0, seconds: 0 };
    }

    const checkinDate = new Date(record.checkinTime);
    const diffMs = currentTime - checkinDate;
    const totalSeconds = Math.floor(diffMs / 1000);

    const hours = Math.floor(totalSeconds / 3600);
    const minutes = Math.floor((totalSeconds % 3600) / 60);
    const seconds = totalSeconds % 60;

    return { hours, minutes, seconds };
  };

  return (
    <div className="min-h-screen bg-moura-blue flex items-center justify-center p-4">
      <Card className="text-center">
        <img
          src="/logo-moura.png"
          alt="Moura"
          className="h-10 mx-auto mb-6"
        />

        <h2 className="text-xl font-bold mb-1 text-gray-800">
          Registro de Ponto
        </h2>

        {status === "idle" && (
          <>
            <p className="text-gray-500 mb-8 text-sm">
              Seja bem-vindo,{" "}
              <span className="font-bold">{user?.name}</span>.
            </p>

            <Button onClick={handleAction} icon={Clock} disabled={loading}>
              {loading ? "Processando..." : "Check-in"}
            </Button>
          </>
        )}

        {status === "active" && (
          <>
            <p className="text-gray-500 mb-4 text-xs font-medium italic">
              {user?.name}, seu check-in foi realizado às
            </p>

            <div className="text-5xl font-bold text-moura-orange mb-4 italic">
              {new Date(record?.checkinTime).toLocaleTimeString("pt-BR", {
                hour: "2-digit",
                minute: "2-digit",
              })}
            </div>

            <div className="mb-6 text-sm text-gray-600">
              <p className="mb-1">Tempo trabalhando:</p>
              <p className="text-3xl font-bold text-gray-800 tabular-nums">
                {String(calculateDuration().hours).padStart(2, "0")}:
                {String(calculateDuration().minutes).padStart(2, "0")}:
                {String(calculateDuration().seconds).padStart(2, "0")}
              </p>
            </div>

            <Button onClick={handleAction} icon={Clock} disabled={loading}>
              {loading ? "Processando..." : "Check-out"}
            </Button>
          </>
        )}

        {status === "finished" && (
          <div className="space-y-4">
            <p className="text-moura-blue font-medium italic">
              Seu checkout foi realizado!
            </p>

            <div className="grid grid-cols-2 gap-2">
              <div className="bg-gray-100 p-2 rounded-lg">
                <p className="text-[10px] text-gray-400">Entrada</p>
                <p className="font-bold text-gray-800">
                  {new Date(record?.checkinTime).toLocaleTimeString("pt-BR", {
                    hour: "2-digit",
                    minute: "2-digit",
                  })}
                </p>
              </div>

              <div className="bg-gray-100 p-2 rounded-lg">
                <p className="text-[10px] text-gray-400">Saída</p>
                <p className="font-bold text-gray-800">
                  {new Date(record?.checkoutTime).toLocaleTimeString("pt-BR", {
                    hour: "2-digit",
                    minute: "2-digit",
                  })}
                </p>
              </div>
            </div>

            <div className="bg-orange-50 p-4 rounded-xl">
              <p className="text-[10px] text-gray-400 uppercase font-bold">
                Total trabalhado hoje
              </p>
              <p className="text-2xl font-bold text-moura-orange">
                {Math.floor((record?.duration || 0) / 60)}h{" "}
                {(record?.duration || 0) % 60}min
              </p>
            </div>

            <Button
              onClick={() => {
                setStatus("idle");
                setRecord(null);
              }}
              icon={Clock}
              className="mt-4"
            >
              Novo Registro
            </Button>
          </div>
        )}

        <button
          onClick={logout}
          className="mt-8 flex items-center justify-center gap-2 text-gray-400 text-xs hover:underline w-full"
        >
          <LogOut size={14} /> Sair
        </button>
      </Card>
    </div>
  );
}

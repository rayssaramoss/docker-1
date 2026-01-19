import React, { useEffect, useState } from "react";
import { Search, RotateCcw, LogOut } from "lucide-react";
import { useAuth } from "../contexts/AuthContext";
import api from "../api/api";
import { Button } from "../components/button";

export default function Admin() {
  const { logout } = useAuth();

  const [records, setRecords] = useState([]);
  const [filterName, setFilterName] = useState("");
  const [filterDate, setFilterDate] = useState("");
  const [loading, setLoading] = useState(false);

  const fetchRecords = async (name = "", date = "") => {
    setLoading(true);

    try {
      // CORREÇÃO AQUI: Montamos os parâmetros para enviar ao Backend
      const params = { 
        page: 0, 
        size: 100 
      };

      if (name.trim()) params.name = name;
      // Enviamos 'dateStr' que é o nome que o Backend (Service) gosta
      if (date.trim()) params.dateStr = date; 

      console.log("Enviando para API:", params); // Debug no navegador

      const response = await api.get("/work/list", { params });

      // O Backend já filtrou, então confiamos na resposta dele
      let data = response.data.content || response.data;
      setRecords(data);

    } catch (error) {
      console.error("Erro na API, usando dados falsos:", error);
      
      // FALLBACK (Dados Falsos) caso o backend esteja off
      let mockData = [
        {
          id: 1,
          employee: { name: "João Silva" },
          checkinTime: "2026-01-17T08:00:00",
          checkoutTime: "2026-01-17T17:30:00",
          duration: 480,
        },
        // ... (outros dados mock)
      ];
      // ... (lógica de filtro local do mock mantida igual)
      setRecords(mockData);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRecords();
  }, []);

  const handleFilter = () => {
    fetchRecords(filterName, filterDate);
  };

  const handleClearFilters = () => {
    setFilterName("");
    setFilterDate("");
    fetchRecords("", ""); // Chama sem filtros para resetar
  };

  const formatDateTime = (dateString) => {
    if (!dateString) return "--";
    return new Date(dateString).toLocaleString("pt-BR", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
    });
  };

  return (
    <div className="min-h-screen bg-moura-blue p-4 md:p-8">
      <header className="bg-white rounded-xl p-4 flex justify-between items-center mb-6 shadow-lg">
        <img src="/logo-moura.png" alt="Moura" className="h-8" />
        <button
          onClick={logout}
          className="flex items-center gap-2 text-gray-600 hover:text-moura-blue transition-colors"
        >
          <LogOut size={18} /> Sair
        </button>
      </header>

      <main className="bg-white rounded-2xl p-6 shadow-2xl max-w-6xl mx-auto">
        <h1 className="text-2xl font-bold text-gray-800 mb-1">
          Painel Administrativo
        </h1>
        <p className="text-gray-500 text-sm mb-8">
          Visualize e filtre os registros dos funcionários
        </p>

        <div className="bg-gray-100 p-4 rounded-xl flex flex-wrap gap-4 items-end mb-8">
          <div className="flex-1 min-w-[200px]">
            <label className="text-xs font-bold text-gray-600 block mb-1">
              Nome
            </label>
            <div className="relative">
              <Search
                className="absolute left-3 top-3 text-gray-400"
                size={18}
              />
              <input
                type="text"
                placeholder="Filtrar por nome"
                className="w-full pl-10 pr-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-[#F2A933]"
                value={filterName}
                onChange={(e) => setFilterName(e.target.value)}
                onKeyDown={(e) => e.key === "Enter" && handleFilter()}
              />
            </div>
          </div>

          <div>
            <label className="text-xs font-bold text-gray-600 block mb-1">
              Data
            </label>
            <input
              type="date"
              className="p-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-[#F2A933]"
              value={filterDate}
              onChange={(e) => setFilterDate(e.target.value)}
            />
          </div>

          <Button
            onClick={handleFilter}
            className="w-auto px-8 py-2"
            disabled={loading}
          >
            {loading ? "Carregando..." : "Filtrar"}
          </Button>

          <button
            onClick={handleClearFilters}
            className="flex items-center gap-2 text-gray-500 text-sm hover:text-moura-blue p-2 transition-colors"
            disabled={loading}
          >
            <RotateCcw size={16} /> Limpar
          </button>
        </div>

        <div className="overflow-x-auto">
          <table className="w-full text-left">
            <thead>
              <tr className="border-b text-gray-400 text-sm">
                <th className="pb-4 font-medium">Funcionário</th>
                <th className="pb-4 font-medium">Check-in</th>
                <th className="pb-4 font-medium">Check-out</th>
                <th className="pb-4 font-medium text-right">Duração</th>
              </tr>
            </thead>

            <tbody className="text-gray-700">
              {records.map((reg) => (
                <tr
                  key={reg.id}
                  className="border-b last:border-0 hover:bg-gray-50 transition-colors"
                >
                  <td className="py-4 font-bold">
                    {reg.employee?.name || "---"}
                  </td>
                  <td className="py-4 text-sm">
                    {formatDateTime(reg.checkinTime)}
                  </td>
                  <td className="py-4 text-sm">
                    {reg.checkoutTime ? (
                      formatDateTime(reg.checkoutTime)
                    ) : (
                      <span className="text-orange-500 font-semibold">
                        Em aberto
                      </span>
                    )}
                  </td>
                  <td className="py-4 text-right font-bold text-moura-orange">
                    {reg.duration
                      ? `${Math.floor(reg.duration / 60)}h ${
                          reg.duration % 60
                        }min`
                      : "--"}
                  </td>
                </tr>
              ))}

              {records.length === 0 && (
                <tr>
                  <td colSpan="4" className="text-center text-gray-400 py-8">
                    {loading
                      ? "Carregando..."
                      : "Nenhum registro encontrado"}
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>

        <div className="mt-6 text-center text-sm text-gray-400">
          Total: {records.length} registro
          {records.length !== 1 ? "s" : ""}
        </div>
      </main>
    </div>
  );
}
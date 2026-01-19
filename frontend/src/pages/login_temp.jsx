import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import { Card } from "../components/card";
import { Button } from "../components/button";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    const result = await login(email, password);

    if (!result.success) {
      setError(result.message || "Erro ao fazer login");
      setLoading(false);
      return;
    }

    const userRole = result.user?.role;

    if (userRole && userRole.toUpperCase() === "ADMIN") {
      navigate("/admin");
    } else {
      navigate("/ponto");
    }

    setLoading(false);
  };

  return (
    <div className="min-h-screen bg-[#003366] flex items-center justify-center p-4">
      <Card>
        <div className="flex flex-col items-center mb-8">
          <img src="/logo-moura.png" alt="Moura" className="h-12 mb-4" />
          <h2 className="text-xl font-bold text-gray-800">
            Registros de Ponto
          </h2>
          <p className="text-sm text-[#003366] opacity-70">
            Faça login para acessar o sistema
          </p>
        </div>

        {error && (
          <div className="mb-4 p-3 bg-red-100 border border-red-400 text-red-700 rounded-lg text-sm">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-6">
          <input
            type="email"
            placeholder="Email"
            className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-[#F2A933]"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />

          <input
            type="password"
            placeholder="Senha"
            className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-[#F2A933]"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          <Button type="submit" disabled={loading}>
            {loading ? "Entrando..." : "Entrar"}
          </Button>
        </form>

        <div className="mt-6 p-4 bg-gray-50 rounded-lg text-xs text-gray-600">
          <p className="font-semibold mb-2 text-center">
            Usuários de teste
          </p>
          <div className="space-y-1 text-center">
            <p>
              <strong>Admin:</strong> admin@moura.com / 123
            </p>
            <p>
              <strong>Funcionário:</strong> funcionario@moura.com / 123
            </p>
          </div>
        </div>
      </Card>
    </div>
  );
}

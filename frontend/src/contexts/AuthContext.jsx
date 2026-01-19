import React, { createContext, useContext, useState } from "react";
import api from "../api/api";

const AuthContext = createContext({});

const DEV_MODE = false; // true apenas se o backend não estiver disponível

const fakeUsers = [
  {
    id: 1,
    name: "Gestor Moura",
    email: "admin@moura.com",
    password: "123",
    role: "ADMIN",
    token: "fake-admin-token",
  },
  {
    id: 2,
    name: "João Silva",
    email: "funcionario@moura.com",
    password: "123",
    role: "USER",
    token: "fake-user-token",
  },
];

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = async (email, password) => {
    if (DEV_MODE) {
      const foundUser = fakeUsers.find(
        (u) => u.email === email && u.password === password
      );

      if (!foundUser) {
        return { success: false, message: "Email ou senha inválidos" };
      }

      setUser(foundUser);
      return { success: true, user: foundUser };
    }

    try {
      const response = await api.post("/auth/login", { email, password });

      const userData = {
        id: response.data.id,
        name: response.data.name,
        token: response.data.token,
        role: response.data.role || "USER",
      };

      api.defaults.headers.common["Authorization"] = `Bearer ${userData.token}`;
      setUser(userData);

      return { success: true, user: userData };
    } catch (error) {
      console.warn("Backend indisponível. Usando fallback local.");

      const foundUser = fakeUsers.find(
        (u) => u.email === email && u.password === password
      );

      if (!foundUser) {
        return { success: false, message: "Email ou senha inválidos" };
      }

      setUser(foundUser);
      return { success: true, user: foundUser };
    }
  };

  const logout = () => {
    setUser(null);
    delete api.defaults.headers.common["Authorization"];
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        login,
        logout,
        authenticated: !!user,
        isAdmin: user?.role === "ADMIN",
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

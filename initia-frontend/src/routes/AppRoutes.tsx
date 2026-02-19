// src/routes/AppRoutes.tsx
import { Routes, Route as RouterRoute, Navigate } from 'react-router-dom';

import MainLayout from '../layout/MainLayout';
import ClientsList from '../pages/ClientsList';
import NotFound from '../components/NotFound';

export default function AppRoutes() {
  return (
    <Routes>
      <RouterRoute element={<MainLayout />}>
        <RouterRoute index element={<Navigate to="/clients" replace />} />
        <RouterRoute path="clients" element={<ClientsList />} />
        <RouterRoute path="*" element={<NotFound />} />
      </RouterRoute>
    </Routes>
  );
}
// src/App.tsx
import { QueryClientProvider } from '@tanstack/react-query';
import { Toaster } from 'react-hot-toast';
import { queryClient } from './lib/queryClient';
import AppRoutes from './routes/AppRoutes';
import './index.css';

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <AppRoutes />                    {/* ← just the routes */}
      <Toaster position="top-right" toastOptions={{ duration: 4000 }} />
    </QueryClientProvider>
  );
}

export default App;
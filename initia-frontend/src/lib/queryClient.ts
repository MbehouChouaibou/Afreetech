// src/lib/queryClient.ts
import { QueryClient } from '@tanstack/react-query';
import toast from 'react-hot-toast';

export const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1,
      staleTime: 1000 * 60 * 5,
      // Global error handler → runs for EVERY failed query
      throwOnError: false, // optional – prevents automatic error boundary throw
      // But we handle errors manually in components or here
    },
  },
  // You can also use queryCache for global side effects
  // queryCache: new QueryCache({
  //   onError: (error) => {
  //     toast.error(error.message || 'Erreur serveur');
  //   },
  // }),
});
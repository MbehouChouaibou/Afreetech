
import { useQuery } from '@tanstack/react-query';
import api from '../actions/api';
import { type ClientDTO } from '../types/ClientTypes'; 
export function useClients() {
  return useQuery<ClientDTO[], Error>({
    queryKey: ['clients'],
    queryFn: async () => {
      const response = await api.get<ClientDTO[]>('/clients');
      return response.data; 
    },
  });
}
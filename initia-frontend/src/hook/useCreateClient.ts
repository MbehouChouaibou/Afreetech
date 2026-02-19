import { useMutation, useQueryClient } from '@tanstack/react-query';
import api from '../actions/api';
import {type ClientDTO } from '../types/ClientTypes';
import toast from 'react-hot-toast';

export function useCreateClient() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async (newClient: Omit<ClientDTO, 'id'>) => {
      const response = await api.post<ClientDTO>('/clients/createclient', newClient);
      return response.data;
    },

    onSuccess: (createdClient) => {
      // Rafraîchir la liste des clients
      queryClient.invalidateQueries({ queryKey: ['clients'] });
      
      toast.success(`Client ${createdClient.nom} ${createdClient.prenom} créé avec succès !`);
    },

    onError: (error: any) => {
      const message = error.response?.data?.message || error.message || 'Erreur lors de la création';
      toast.error(message, { duration: 6000 });
      console.error('Erreur création client:', error);
    },
  });
}
import api from './api'; 
import { type ClientDTO } from '../types/ClientTypes'; // adjust path if needed

export const createClient = async (clientData: Omit<ClientDTO, 'id'>): Promise<ClientDTO> => {
  const response = await api.post('/clients/createclient', clientData);
  return response.data;
};
// src/features/clients/pages/ClientsList.tsx
import { useState } from 'react';
import { useClients } from '../hook/useClients';
import ClientTable from '../components/ClientTable';
import ClientForm from '../components/forms/ClientForm'; 
import Modal from '../components/ui/Modal'; // adjust path
import { useCreateClient } from '../hook/useCreateClient';
import { Users, Plus } from 'lucide-react';
import toast from 'react-hot-toast';

export default function ClientsList() {
  const { data: clients = [], isLoading, isError, error } = useClients();
  const { mutate, isPending } = useCreateClient();

  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleCreateClient = (formData: any) => {
    mutate(formData, {
      onSuccess: () => {
        setIsModalOpen(false); // close modal on success
        toast.success('Client créé avec succès !');
      },
    });
  };

  if (isLoading) return <div className="text-center py-12">Chargement...</div>;

  if (isError) {
    const msg = error?.message || 'Erreur de chargement';
    toast.error(msg);
    return <div className="text-red-600 text-center py-12">{msg}</div>;
  }

  return (
    <div className="space-y-6">
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div className="flex items-center">
          <Users className="w-8 h-8 text-indigo-600 mr-3" />
          <h1 className="text-2xl font-bold text-gray-900">Liste des Clients</h1>
        </div>

        <button
          onClick={() => setIsModalOpen(true)}
          className="inline-flex items-center px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700 transition shadow-sm"
        >
          <Plus size={18} className="mr-2" />
          Nouveau Client
        </button>
      </div>

      <ClientTable clients={clients} />

      {/* Modal with reusable form */}
      <Modal
  isOpen={isModalOpen}
  onClose={() => setIsModalOpen(false)}
  title="Créer un nouveau client"
>
  <ClientForm
    onSubmit={(data) => {
      // Envoie exactement le format attendu par l'API
      mutate({
        ...data,
        assuranceIds: data.assuranceIds || [], // toujours un tableau
      });
    }}
    isSubmitting={isPending}
    submitButtonText="Créer le client"
  />
</Modal>
    </div>
  );
}
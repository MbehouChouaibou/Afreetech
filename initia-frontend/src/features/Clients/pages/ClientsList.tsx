import { useEffect } from 'react';
import { Users } from 'lucide-react';

export default function ClientsList() {
  useEffect(() => {
    // We'll replace this with real data fetching later
    console.log('Clients list page loaded');
  }, []);

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-gray-900">Liste des Clients</h1>
        <button className="bg-indigo-600 text-white px-4 py-2 rounded-md hover:bg-indigo-700 transition">
          + Nouveau Client
        </button>
      </div>

      <div className="bg-white shadow rounded-lg overflow-hidden">
        <div className="p-6 text-center text-gray-500">
          <Users className="w-16 h-16 mx-auto mb-4 text-gray-400" />
          <p>Aucun client pour le moment</p>
          <p className="text-sm mt-2">Cliquez sur "Nouveau Client" pour commencer</p>
        </div>
      </div>
    </div>
  );
}
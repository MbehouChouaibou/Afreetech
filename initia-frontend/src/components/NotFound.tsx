import { Link } from 'react-router-dom';
import { AlertCircle } from 'lucide-react';

export default function NotFound() {
  return (
    <div className="min-h-[60vh] flex flex-col items-center justify-center text-center px-4">
      <AlertCircle className="w-20 h-20 text-red-500 mb-6" />
      <h1 className="text-6xl font-bold text-gray-800 mb-4">404</h1>
      <h2 className="text-2xl font-semibold text-gray-700 mb-6">
        Page non trouvée
      </h2>
      <p className="text-gray-600 mb-8 max-w-md">
        La page que vous recherchez semble introuvable ou a été déplacée.
      </p>
      <Link
        to="/clients"
        className="bg-indigo-600 text-white px-6 py-3 rounded-lg hover:bg-indigo-700 transition-colors"
      >
        Retour à la liste des clients
      </Link>
    </div>
  );
}
// src/components/forms/ClientForm.tsx
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import * as z from 'zod';

// ────────────────────────────────────────────────
// Schéma Zod corrigé : assuranceIds est obligatoire (toujours tableau, vide OK)
// ────────────────────────────────────────────────
const clientSchema = z.object({
  nom: z.string().min(2, 'Le nom est requis (min 2 caractères)'),
  prenom: z.string().min(2, 'Le prénom est requis'),
  email: z.string().email('Email invalide'),
  telephone: z.string().optional(),
  adresse: z.string().optional(),
  dateNaissance: z.string().optional(),
  assuranceIds: z.array(z.number()).default([]), // ← plus de ? → toujours présent
});

type ClientFormData = z.infer<typeof clientSchema>;

// ────────────────────────────────────────────────
// Props (onSubmit attend maintenant ClientFormData)
// ────────────────────────────────────────────────
interface ClientFormProps {
  onSubmit: (data: ClientFormData) => void;
  isSubmitting?: boolean;
  defaultValues?: Partial<ClientFormData>;
  submitButtonText?: string;
}

export default function ClientForm({
  onSubmit,
  isSubmitting = false,
  defaultValues = {},
  submitButtonText = 'Créer le client',
}: ClientFormProps) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<ClientFormData>({
    resolver: zodResolver(clientSchema),
    defaultValues: {
      nom: '',
      prenom: '',
      email: '',
      telephone: '',
      adresse: '',
      dateNaissance: '',
      assuranceIds: [], // ← tableau vide → type cohérent
      ...defaultValues,
    },
  });

  const onFormSubmit = (data: ClientFormData) => {
    onSubmit(data); // plus besoin de ?? [] → Zod garantit déjà que c'est un tableau
  };

  return (
    <form onSubmit={handleSubmit(onFormSubmit)} className="space-y-6">
      <div>
        <label className="block text-sm font-medium text-gray-700">Nom *</label>
        <input
          {...register('nom')}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        />
        {errors.nom && <p className="mt-1 text-sm text-red-600">{errors.nom.message}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700">Prénom *</label>
        <input
          {...register('prenom')}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        />
        {errors.prenom && <p className="mt-1 text-sm text-red-600">{errors.prenom.message}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700">Email *</label>
        <input
          type="email"
          {...register('email')}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        />
        {errors.email && <p className="mt-1 text-sm text-red-600">{errors.email.message}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700">Téléphone</label>
        <input
          {...register('telephone')}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        />
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700">Adresse</label>
        <input
          {...register('adresse')}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        />
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700">Date de naissance</label>
        <input
          type="date"
          {...register('dateNaissance')}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        />
      </div>

      {/* Si tu veux afficher / éditer assuranceIds plus tard */}
      {/* <div>
        <label className="block text-sm font-medium text-gray-700">IDs des assurances (séparés par virgule)</label>
        <input
          type="text"
          placeholder="ex: 1,3,5"
          {...register('assuranceIds')}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
        />
      </div> */}

      <button
        type="submit"
        disabled={isSubmitting}
        className="w-full bg-indigo-600 text-white py-2.5 px-4 rounded-md hover:bg-indigo-700 disabled:opacity-50 transition-colors"
      >
        {isSubmitting ? 'Création en cours...' : submitButtonText}
      </button>
    </form>
  );
}
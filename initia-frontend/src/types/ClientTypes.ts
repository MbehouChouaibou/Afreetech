export interface ClientDTO {
  id?: number;               // optionnel lors de la création
  nom: string;
  prenom: string;
  dateNaissance?: string;    // format YYYY-MM-DD
  adresse?: string;
  telephone?: string;
  email: string;
  assuranceIds?: number[];   // ← ajouté comme dans ton exemple JSON
}
export interface ClientCreateDTO {
  nom: string;
  prenom: string;
  dateNaissance?: string;      // "YYYY-MM-DD"
  adresse?: string;
  telephone?: string;
  email: string;
  assuranceIds?: number[];     // tableau vide ou avec IDs
}
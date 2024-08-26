export type UserStatus = 'ACTIVE' | 'INACTIVE';

export interface User {
  id: number;
  nom: string;
  prenom: string;
  username: string;
  role: string;
  status: UserStatus;
}

import { User } from "./User";

export interface Vehicule{
    id:number;
    marque:string;
    modele:string;
    plaqueImmatriculation:string;
    dateAchat:Date;
    type:string;
    etat:string;
    userId:User;

}
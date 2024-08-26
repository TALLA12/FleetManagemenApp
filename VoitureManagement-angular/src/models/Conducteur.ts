import { User } from "./User";
import { Vehicule } from "./Vehicule";

export interface Conducteur{
    id:number;
    nom:string;
    prenom:string;
    dateNaissance:Date;
    NumeroTelephone:string;
    adresse:string;
    email:string;
    numeroPermis:string;
    dateDelivrancePermis:Date;
    dateExpirationPermis:Date;
    typePermis:string;
    dateDebutEmploi:Date;
    statutEmploi:string;
    vehiculeAssigneId:Vehicule;
    userId:User;
}
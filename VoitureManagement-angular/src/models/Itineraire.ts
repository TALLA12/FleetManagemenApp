import { Conducteur } from "./Conducteur";
import { User } from "./User";


export interface Itineraire {
    id:number
    origine:string;
    destination:string;
    distance:number;
    duree:number;
    dateDepart:Date;
    dateArrivee:Date; 
    conducteurId:Conducteur;
    userId: User;
}
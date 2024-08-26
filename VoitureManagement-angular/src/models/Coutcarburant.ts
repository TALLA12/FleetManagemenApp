import { User } from "./User";
import { Vehicule } from "./Vehicule";

export interface Coutcarburant{
    id:number;
    date:Date;
    quantite:number;
    prix:number;
    total:number;
    vehiculeId:Vehicule;
    userId:User;
}
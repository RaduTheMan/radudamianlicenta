import { Average } from './average.type';
import { Details } from './details.type';
import { Visuals } from './visuals.type';

export type Game = {
  uuid: string;
  title: string;
  genres: string[];
  visuals: Visuals;
  details: Details;
  average: Average;
};

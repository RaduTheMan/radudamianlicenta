export type Details = {
    involvedCompanies: string[];
    platforms: string[];
    releaseDates: {
        platformName: string;
        humanDate: string;
    }[];
    gameModes: string[];
};
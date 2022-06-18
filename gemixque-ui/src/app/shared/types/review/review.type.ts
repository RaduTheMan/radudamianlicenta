export type Review = {
    content: string;
    score: string;
    time: string;
    gameEntity?: {
        title: string;
        uuid: string;
    };
    userEntity?: {
        username: string;
        uuid: string;
    };
};
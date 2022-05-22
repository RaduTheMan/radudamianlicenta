export type Paginated<T> = {
    content: T[];
    totalElements: number;
    totalPages: number;
};
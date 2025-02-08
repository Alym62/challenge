export interface PagerResponse<T> {
    content: Array<T>;
    totalElements: number;
    totalPages: number;
    size: number;
    number: number;
}
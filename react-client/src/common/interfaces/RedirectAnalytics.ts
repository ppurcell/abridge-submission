export interface RedirectAnalytics {
    term: string;
    createdDate: string;
    totalRedirects: number;
    redirectByDate: RedirectByDate[];
}

export interface RedirectByDate {
    date: Date;
    redirectCount: number | null;
}

import React from 'react';
import {
    ResponsiveContainer,
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip
} from 'recharts';
import {RedirectByDate} from "../../../common/interfaces/RedirectAnalytics";


interface Props {
    redirectsByDate: RedirectByDate[];
}

/**
 * A line chart component that visualizes the number of redirects over time.
 * @param redirectsByDate - An array of RedirectByDate objects containing date and redirect count.
 */
export const RedirectByDateChart: React.FC<Props> = ({redirectsByDate} : { redirectsByDate: RedirectByDate[]}) => (
    <ResponsiveContainer width="100%" height={300}>
        <LineChart data={redirectsByDate}>
            <CartesianGrid strokeDasharray="3 3"/>
            <XAxis dataKey="date" tickFormatter={formatXAxis} interval={10} name='Time'/>
            <YAxis/>
            <Tooltip/>
            <Line type="monotone" dataKey="redirectCount" stroke="#8884d8"/>
        </LineChart>
    </ResponsiveContainer>
);

const formatXAxis = (tickItem: string | number | Date) => {
    const date = new Date(tickItem);
    return `${date.getMonth() + 1}/${date.getDate()}`; // MM/DD format
};

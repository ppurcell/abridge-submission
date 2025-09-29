import React from 'react';
import { Box, TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper } from '@mui/material';
import {RedirectByDate} from "../../../common/interfaces/RedirectAnalytics";


/**
 * Table component to display redirects grouped by date.
 * @param redirectsByDate - Array of redirect data grouped by date.
 */
const RedirectTable = ({redirectsByDate} : {redirectsByDate: RedirectByDate[] }) => {
    return (
        <Box mt={2}>
            <TableContainer component={Paper}>
                <Table size="small">
                    <TableHead>
                        <TableRow>
                            <TableCell>Date</TableCell>
                            <TableCell align="right">Redirects</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {redirectsByDate.map(({ date, redirectCount }) => (
                            <TableRow key={date.toString()}>
                                <TableCell>{new Date(date).toLocaleDateString()}</TableCell>
                                <TableCell align="right">{redirectCount}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

export default RedirectTable;

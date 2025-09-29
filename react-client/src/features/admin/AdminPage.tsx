import React, {useEffect} from 'react';
import {useSnackbar} from "notistack";
import {
    TableContainer,
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableBody,
    Paper,
    IconButton, CardContent, Card, Container, Typography
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import LaunchIcon from '@mui/icons-material/Launch';
import QueryStatsIcon from '@mui/icons-material/QueryStats';

import {AdminService} from "./AdminService";


//React18 strict mode causes useEffect to run twice in development mode.
let isInitialized = false;

/**
 * AdminPage for managing bridge links.
 */
export const AdminPage = () => {
    const {enqueueSnackbar} = useSnackbar();
    const [bridgeLinks, setBridgeLinks] = React.useState<any[]>([]);

    useEffect(() => {
        async function startFetching() {
            if (isInitialized) {
                return;
            }
            isInitialized = true;
            await AdminService.retrieveBridgeLinks().then(data => {
                setBridgeLinks(data || []);
            }).catch(err => {
                enqueueSnackbar(`Error connecting with server...`, {variant: 'warning'});
            });
        }

        startFetching();

    }, [enqueueSnackbar])

    const handleDelete = async (term: string) => {
        await AdminService.deleteBridgeLink(term).then(() => {
            setBridgeLinks(current => current.filter(link => link.term !== term));
            enqueueSnackbar('Entry deleted', {variant: 'success'});
        }).catch(() => {
            enqueueSnackbar('Failed to delete entry', {variant: 'error'});
        });

    };

    return (
        <Container maxWidth="md">
            <Card
                sx={{
                    borderRadius: 3,
                    boxShadow: "0 8px 24px rgba(0,0,0,0.2)",
                }}
            >
                <CardContent sx={{p: 4}}>
                    <Typography
                        variant="h4"
                        align="center"
                        gutterBottom
                        sx={{color: "primary.main", fontWeight: "bold"}}
                    >
                        Admin Dashboard
                    </Typography>
                    <TableContainer component={Paper}>
                        <Table size="small">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Term</TableCell>
                                    <TableCell>Redirect URL</TableCell>
                                    <TableCell>Created Date</TableCell>
                                    <TableCell align="center">Actions</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {bridgeLinks.map(({id, term, redirectUrl, createdDate}) => (
                                    <TableRow key={id}>
                                        <TableCell>{term}</TableCell>
                                        <TableCell>
                                            <a href={redirectUrl} target="_blank" rel="noopener noreferrer">
                                                {redirectUrl}
                                            </a>
                                        </TableCell>
                                        <TableCell>{new Date(createdDate).toLocaleString()}</TableCell>
                                        <TableCell align="center">
                                            <IconButton onClick={() => handleDelete(term)} size="small">
                                                <DeleteIcon fontSize="small"/>
                                            </IconButton>
                                            <IconButton href={`/${term}/analytics`} target="_blank" size="small">
                                                <QueryStatsIcon fontSize="small"/>
                                            </IconButton>
                                            <IconButton href={`/${term}`} target="_blank" size="small">
                                                <LaunchIcon fontSize="small"/>
                                            </IconButton>
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </CardContent>
            </Card>
        </Container>
    );
};



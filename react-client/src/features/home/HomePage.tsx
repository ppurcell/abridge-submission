import React, {useState} from "react";
import {useSnackbar} from "notistack";
import {Box, Button, Card, CardContent, Container, TextField, Typography,} from "@mui/material";

import {CommonService} from "../../common/CommonService";
import {CLIENT_ROOT} from "../../common/CommonRoutes";
import {CopyPasteShortURL} from "./components/CopyPasteShortUrl";

/**
 * Homepage handles creating new bridge links.
 * @constructor
 */
export default function HomePage() {
    const {enqueueSnackbar} = useSnackbar();

    const [url, setUrl] = useState("");
    const [term, setTerm] = useState("");
    const [shortUrl, setShortUrl] = useState("");

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (!url || !term) return;

        //TODO. Convert this to an interface object.
        CommonService.saveBridgeLink(url, term).then(r => {
            if (r !== undefined) {
                setShortUrl(CLIENT_ROOT + term);
            }
        }).catch(err => {
            if(err?.response?.status === 409) {
                enqueueSnackbar(`Term "${term}" is already in use. Please try another term.`, {variant: 'warning'});
            } else {
                enqueueSnackbar(`Error connecting with server...`, {variant: 'warning'});
            }

        })
        ;
    };

    return (
        <>
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
                            The Internet (Abridged)
                        </Typography>

                        <Typography
                            variant="body1"
                            align="center"
                            sx={{mb: 3, color: "text.secondary"}}
                        >
                            Create a bridge to your content. Shorten and share links with ease.
                        </Typography>

                        <Box
                            component="form"
                            onSubmit={handleSubmit}
                            sx={{display: "flex", gap: 2, mb: 2}}
                        >
                            <TextField
                                fullWidth
                                type="url"
                                label="Enter URL"
                                value={url}
                                onChange={(e) => setUrl(e.target.value)}
                                required
                                color="secondary"
                            />
                            <TextField
                                fullWidth
                                label="Enter Bridge Term"
                                value={term}
                                onChange={(e) => setTerm(e.target.value)}
                                slotProps={{
                                    htmlInput: {maxLength: 6}
                                }}
                                required
                                color="secondary"
                            />
                            <Button
                                type="submit"
                                variant="contained"
                                color="secondary"
                                sx={{px: 3}}
                            >
                                Shorten
                            </Button>
                        </Box>
                        <CopyPasteShortURL shortUrl={shortUrl}/>

                    </CardContent>
                </Card>
            </Container>
        </>
    );
}

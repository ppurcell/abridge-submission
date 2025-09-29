import React from 'react';
import {Box, IconButton, Link} from "@mui/material";
import ContentCopyIcon from "@mui/icons-material/ContentCopy";
import {useSnackbar} from "notistack";

/**
 * Component to display a short URL with a copy-to-clipboard button.
 * @param shortUrl - The short URL to be displayed and copied.
 */
export const CopyPasteShortURL = ({shortUrl}: { shortUrl: string }) => {
    const {enqueueSnackbar} = useSnackbar();

    const handleCopy = async () => {
        if (shortUrl) {
            await navigator.clipboard.writeText(shortUrl);
            enqueueSnackbar('Link copied to clipboard!', {variant: 'success'});

        }
    };

    return (
        <>
            {shortUrl && (
                <Box
                    textAlign="center"
                    mt={3}
                    sx={{
                        backgroundColor: "rgba(103, 58, 183, 0.1)",
                        p: 2,
                        borderRadius: 2,
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        gap: 1,
                    }}
                >
                    <Link
                        href={shortUrl}
                        target="_blank"
                        rel="noopener"
                        sx={{fontSize: "1.1rem", fontWeight: "bold"}}
                    >
                        {shortUrl}
                    </Link>
                    <IconButton size="small" onClick={handleCopy} color="secondary">
                        <ContentCopyIcon fontSize="small"/>
                    </IconButton>
                </Box>
            )}
        </>
    );
};

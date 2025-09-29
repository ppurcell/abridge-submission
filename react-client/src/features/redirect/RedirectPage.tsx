import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {Card, CardContent, Container, Typography} from "@mui/material";
import {CommonService} from "../../common/CommonService";
import {useSnackbar} from "notistack";

//React18 strict mode causes useEffect to run twice in development mode.
let isInitialized = false;

/**
 * Page that handles redirection based on a bridge term.
 * (Will redirect after a short countdown if the term is valid.)
 */
export const RedirectPage = () => {
    const {bridgeTerm} = useParams();
    const {enqueueSnackbar} = useSnackbar();

    const [seconds, setSeconds] = useState(5);
    const [bridgeLinkInfo, setBridgeLinkInfo] = useState<any>(undefined);
    const [didFetch, setDidFetch] = useState(false);

    // Initial data fetching.
    useEffect(() => {
        async function startFetching() {
            if (isInitialized || !bridgeTerm) {
                return;
            }
            isInitialized = true;
            await CommonService.getBridgeLink({bridgeTerm}).then(data => {
                setDidFetch(true);
                setBridgeLinkInfo(data);
            }).catch(err => {
                enqueueSnackbar(`Error connecting with server...`, {variant: 'warning'});
            });
        }

        startFetching();

    }, [bridgeTerm, didFetch, enqueueSnackbar]);

    // Countdown and redirect logic
    useEffect(() => {
        if (didFetch && seconds <= 0) {
            window.location.href = bridgeLinkInfo?.redirectUrl || '/404';
        } else if (didFetch && bridgeLinkInfo !== undefined) {
            let promise = new Promise(resolve => {
                setTimeout(resolve, 1000);
            });
            promise.then(() => setSeconds(seconds - 1));
        }
    }, [bridgeTerm, bridgeLinkInfo, seconds, didFetch]);

    // Determine appropriate message to user.
    let messageToUser = "Loading...";
    if (didFetch && bridgeLinkInfo === undefined) {
        messageToUser = `No link found for term "${bridgeTerm}".`;
    } else if (bridgeLinkInfo) {
        messageToUser = `Redirecting to ${bridgeLinkInfo.redirectUrl} in ${seconds} seconds...`;
    }

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
                            {messageToUser}
                        </Typography>
                    </CardContent>
                </Card>
            </Container>
        </>
    );
};



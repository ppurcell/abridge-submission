import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {useSnackbar} from "notistack";
import {Card, CardContent, Container, Typography} from "@mui/material";

import {CommonService} from "../../common/CommonService";
import {RedirectAnalytics, RedirectByDate} from "../../common/interfaces/RedirectAnalytics";
import {RedirectByDateChart} from "./components/RedirectByDatechart";
import {CommonUtil} from "../../common/utils/CommonUtil";
import RedirectTable from "./components/RedirectTable";

//React18 strict mode causes useEffect to run twice in development mode.
let isInitialized = false;

/**
 * Page to show analytics for a specific bridge link.
 */
export const RedirectAnalyticsPage = () => {
    const {bridgeTerm} = useParams();
    const {enqueueSnackbar} = useSnackbar();

    const [analyticsData, setAnalyticsData] = useState<RedirectAnalytics | undefined>();
    const [graphData, setGraphData] = useState<RedirectByDate[]>([]);

    // Initial data fetching.
    useEffect(() => {
        async function startFetching() {
            if (isInitialized || !bridgeTerm) {
                return;
            }
            isInitialized = true;
            await CommonService.getBridgeLinkAnalytics({bridgeTerm}).then(data => {
                let redirectByDates = CommonUtil.fillMissingDates(data?.redirectByDate || []);
                debugger;
                setGraphData(redirectByDates);
                setAnalyticsData(data);
            }).catch(err => {
                enqueueSnackbar(`Error connecting with server...`, {variant: 'warning'});
            });
        }

        startFetching();

    }, [bridgeTerm, enqueueSnackbar]);

    return (
        <Container maxWidth="md">
            <Card
                sx={{
                    borderRadius: 3,
                    boxShadow: "0 8px 24px rgba(0,0,0,0.2)",
                }}
            >
                <CardContent sx={{p: 4}}>
                    <Typography variant="h4" align="center" gutterBottom>
                        Analytics for {analyticsData?.term}
                    </Typography>
                    <Typography>
                        Created on: {analyticsData && new Date(analyticsData.createdDate).toLocaleString()}
                    </Typography>
                    <Typography>Total redirects: {analyticsData?.totalRedirects}</Typography>
                    <RedirectByDateChart redirectsByDate={graphData || []}/>
                    <RedirectTable redirectsByDate={analyticsData?.redirectByDate || []}/>
                </CardContent>
            </Card>
        </Container>
    );
};


import React from 'react';
import {Box, CssBaseline, ThemeProvider} from "@mui/material";
import brooklynBridge from "../../../assets/brooklyn-bridge.png";
import {createTheme} from "@mui/material/styles";

/**
 * A wrapper component that provides theming and styling for the entire site.
 * @param children - The child components to be wrapped.
 */
const SiteWrapper = ({children}: {children: React.ReactNode}) => {

    const theme = createTheme({
        palette: {
            primary: {
                main: "#be6b5b", // deep purple
            },
            secondary: {
                main: "#be6b5b", // pink accent
            },
            background: {
                default: "#d6dbd3",
            },
        },
        typography: {
            fontFamily: "Roboto, sans-serif",
        },
    });

    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <Box
                sx={{
                    minHeight: "100vh",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    backgroundImage: `url(${brooklynBridge})`,
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                    p: 2,
                }}
            >
            {children}
            </Box>
        </ThemeProvider>
    );
};

export default SiteWrapper;

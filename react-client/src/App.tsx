import {Routes, Route} from 'react-router-dom';
import Home from './features/home/HomePage';
import { AdminPage } from './features/admin/AdminPage';
import {RedirectPage} from "./features/redirect/RedirectPage";
import {CommonClientRoutes} from "./common/CommonRoutes";
import SiteWrapper from "./features/global/hoc/SiteWrapper";
import {ErrorBoundary} from "react-error-boundary";
import {SnackbarProvider} from "notistack";
import {RedirectAnalyticsPage} from "./features/redirect/RedirectAnalyticsPage";

export default function App() {
    function FallbackComponent() {
        return (
            <div role="alert">
                <p>Something went wrong:</p>
            </div>
        );
    }
    return (
        <SiteWrapper>
            <SnackbarProvider maxSnack={3}>
            <ErrorBoundary FallbackComponent={FallbackComponent} >
                <Routes>
                    <Route path={CommonClientRoutes.root()} element={<Home/>}/>
                    <Route path={CommonClientRoutes.admin()} element={<AdminPage/>}/>
                    <Route path={CommonClientRoutes.redirect()} element={<RedirectPage/>}/>
                    <Route path={CommonClientRoutes.redirectAnalytics()} element={<RedirectAnalyticsPage/>}/>
                </Routes>
            </ErrorBoundary>
            </SnackbarProvider>
        </SiteWrapper>
    );


}


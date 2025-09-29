import axios, {AxiosError} from 'axios';

import {CommonAPIRoutes} from "./CommonRoutes";
import BridgeLink from "./interfaces/BridgeLink";
import {RedirectAnalytics} from "./interfaces/RedirectAnalytics";

/**
 * Service class for common API interactions.
 */
export class CommonService {

    /**
     * Saves a bridge link with the provided redirect URL and term.
     */
    static readonly saveBridgeLink = async (redirectUrl: String, term: String): Promise<void> => {
        const formattedURL = CommonAPIRoutes.createBridgeLink();
        try {
            const {data, status} = await axios.put(formattedURL, {"redirectUrl": redirectUrl, "term": term});
             if (status === 200 && data) {
                return data;
            } else {
                return undefined;
            }
        } catch (e) {
            const err = e as AxiosError;
            if (err.response?.status === 404) {
                return undefined;
            }
            throw err;
        }
    }

    /**
     * Retrieves a bridge link by its term. Will send user agent info to the backend.
     */
    static readonly getBridgeLink = async ({bridgeTerm}: { bridgeTerm: string }): Promise<BridgeLink | undefined> => {
        const formattedURL = `${CommonAPIRoutes.getBridgeLink(bridgeTerm)}`;
        try {
        const {data, status} = await axios.post(formattedURL, {userAgentInfo: navigator.userAgent});

            if (status === 200 && data) {
                return data;
            } else {
                return undefined;
            }
        } catch (e) {
            const err = e as AxiosError;
            if (err.response?.status === 404) {
                return undefined;
            }
            throw err;
        }

    };

    /**
     * Grabs analytics for a specific bridge link via its term.
     */
    static readonly getBridgeLinkAnalytics = async ({bridgeTerm}: { bridgeTerm: string }): Promise<RedirectAnalytics | undefined> => {
        const formattedURL = `${CommonAPIRoutes.bridgeLinkAnalytics(bridgeTerm)}`;
        try {
            const {data, status} = await axios.get(formattedURL);

            if (status === 200 && data) {
                return data;
            } else {
                return undefined;
            }
        } catch (e) {
            const err = e as AxiosError;
            if (err.response?.status === 404) {
                return undefined;
            }
            throw err;
        }

    };
}

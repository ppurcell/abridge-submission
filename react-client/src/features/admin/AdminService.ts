import {CommonAPIRoutes} from "../../common/CommonRoutes";
import axios, {AxiosError} from "axios";
import BridgeLink from "../../common/interfaces/BridgeLink";

/**
 * Service class for Admin Page API interactions.
 */
export class AdminService {
    /**
     * Retrieves all bridge links for admin portal.
     */
    static readonly retrieveBridgeLinks = async (): Promise<BridgeLink[]> => {
        const formattedURL = CommonAPIRoutes.listBridgeLinks();
        try {
            const {data, status} = await axios.get(formattedURL);
            if (status === 200 && data) {
                return data;
            } else {
                return [];
            }
        } catch (e) {
            const err = e as AxiosError;
            if (err.response?.status === 404) {
                return [];
            }
            throw err;
        }
    }

    /**
     * Deletes a bridge link by its term.
     * @param term The term of the bridge link to delete.
     */
    static readonly deleteBridgeLink = async (term: string): Promise<void> => {
        const formattedURL = CommonAPIRoutes.deleteBridgeLink(term);
        try {
            const {data, status} = await axios.delete(formattedURL);
            if (status === 204 && data) {
                return data;
            }
        } catch (e) {
            throw e;
        }
    }

}

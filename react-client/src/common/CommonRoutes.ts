export const CLIENT_ROOT = 'http://localhost:3000/';

export const CommonAPIRoutes = {
    listBridgeLinks: (): string => '/bridge-links', //GET
    createBridgeLink: (): string => '/bridge-link', //PUT
    getBridgeLink: (bridgeTerm: string): string => `/bridge-link/${bridgeTerm}`, // GET
    deleteBridgeLink: (term: string): string => `/bridge-link/${term}`, //DELETE
    bridgeLinkAnalytics: (bridgeTerm: string): string => `/bridge-link/${bridgeTerm}/analytics`, //GET
}

export const CommonClientRoutes = {
    root: (): string => '/',
    admin: (): string => '/admin',
    redirect: (): string => `/:bridgeTerm`,
    redirectAnalytics: (): string => `/:bridgeTerm/analytics`,
}

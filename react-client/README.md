# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Getting started
1. npm install
2. npm start
3. Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
4. Make sure your backend server is running at http://localhost:5000
5. Enjoy!

## Available Pages
- Home Page: '/' (Create a new BridgeLink)
- Admin Dashboard: '/admin' (View all BridgeLinks and their analytics)
- Redirect PAge: '/:bridgeLink' (Redirect to the original URL)
- Analytics Dashboard: '/analytics' (View analytics for a specific BridgeLink)

## LLM Usage (CoPilot + Google Bard)
- Example of countdown element in react. (Small useEffect with setInterval)
- Generate scaffold for "small prompt description" (Beginning basis for Homepage.tsx, that I chopped up and customized)
- Some small prompts for layout. (EG, here is a data format, create a table to display it.)
- Create a datapoint for every date to put into recharts. (Quick solve for recharts limitation)
- TLDR: 80-90% Quick mark-up scaffolding to quickly get data looking more presentable given the time constraint.

## Frameworks/Libraries Used
- React / Typescript
- Axios (for API calls)
- React Router (for routing)
- Material UI (for UI components)
- Recharts (for charts in analytics dashboard)

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

## Scenarios
- [X] Create a new BridgeLink
- [X] RedirectPage to BridgeLink
- [X] BridgeLink does not exist
- [X] Server is down
- [X] Model for BridgeLink Audit Logs
- [X] Send Analytics info to API on redirect
- [X] BridgeLink is already taken
- [X] Analytics Dashboard 
- [X] Admin Dashboard

## TODO

- [ ] Document LLM Usage
- [ ] Test new seeding protocol. "update"

## Future (Things I might have liked to show off but ran out of time)
- [ ] Fully testing/implementing site-wide Error Boundary
- [ ] Unit Tests (Jest + React Testing Library)
- [ ] Interfaces for API data
- [ ] Integration Tests (Cypress)
- [ ] ESLint
- [ ] Husky + Prettier (Common Formatting Compliance)
- [ ] Storybook (Component Library)
- [ ] Tree Shaking via Barrel Files


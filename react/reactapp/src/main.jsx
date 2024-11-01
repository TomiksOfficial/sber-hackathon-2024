import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import React from 'react'
import { StoreContext } from './store/StoreContext'
import RootStore from './store/RootStore'

createRoot(document.getElementById('root')).render(
  <React.StrictMode>
	<StoreContext.Provider value={new RootStore()}>
    	<App />
	</StoreContext.Provider>
  </React.StrictMode>,
)

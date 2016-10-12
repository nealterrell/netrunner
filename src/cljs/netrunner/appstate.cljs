(ns netrunner.appstate)

(def app-state
  (atom {:active-page "/"
         :user (js->clj js/user :keywordize-keys true)
         :cards [] :sets [] :mwl []
         :decks [] :decks-loaded false
         :games [] :gameid nil :messages []}))
(ns jinteki.fools)

(def animal-teams {:dog      {:name  "Team Dog"
                              :cards #{}
                              :nickname "bow-wow"}
                   :cat      {:name  "Cat"
                              :cards #{""}
                              :nickname "kitty meow"}
                   :snake    {:name  "Team Snake"
                              :cards #{"Susanoo-no-Mikoto" "Cobra" "Viper" "Mamba" "Caduceus"}
                              :nickname "snek"}
                   :lizard   {:name  "Team Lizard"
                              :cards #{}}
                   :turtle   {:name  "Team Turtle"
                              :cards #{"Aumakua" "Turtlebacks" "Shell Corporation"}}
                   :ungulate {:name  "Ungulate"
                              :cards #{"Celebrity Gift" "Grim" "Trojan Horse" "Patron"}
                              :nickname "whinnie"}
                   :bee      {:name  "Bee"
                              :cards #{"Plan B" "Honeyfarm" "Chrysalis" "Hive" "Hivemind" "Swarm"}
                              :nickname "buzz buzz"}
                   :bird     {:name  "Bird"
                              :cards #{}}
                   :whale    {:name  "Whale"
                              :cards #{}}})

(defn team-cards [team]
  (get-in animal-teams [team :cards]))

(defn team-nickname [team]
  (get-in animal-teams [team :nickname]))

(defn team-name [team]
  (get-in animal-teams [team :name]))

(defn parse-name [s]
  #?(:clj (reduce + (map int s))
     :cljs (reduce + (map #(.charCodeAt % 0) s))))

(defn animal-team [{:keys [username] :as user}]
  (nth (keys animal-teams)
       (mod (parse-name username)
            (count animal-teams))))

(defn animal-username [{:keys [username] :as user}]
  (let [team (animal-team user)]
    (str "[" (team-name team) "] " username)))

(defonce animal-scores (atom (zipmap
                              (keys animal-teams)
                              (replicate (count animal-teams) 0))))

(defn increment-score
  "Takes a team name as a symbol, and increments that team's score by 1."
  [team]
  (swap! animal-scores
         (fn [score-dict] (assoc score-dict team (inc (score-dict team))))))

(defn get-score
  "Takes a team name as a symbol, and returns that team's score."
  [team]
  (@animal-scores team))

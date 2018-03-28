(ns jinteki.fools)

(def animal-teams {:dog      {:name  "Team Dog"
                              :cards #{"Lycan"}
                              :nickname "bow-wow"}
                   :cat      {:name  "Cat"
                              :cards #{"BlacKat" "Rumor Mill"}
                              :nickname "kitty meow"}
                   :snake    {:name  "Team Snake"
                              :cards #{"Susanoo-no-Mikoto" "Cobra" "Viper" "Mamba" "Caduceus"}
                              :nickname "snek"}
                   :lizard   {:name  "Team Lizard"
                              :cards #{"Financial Collapse" "Shattered Remains"}}
                   :turtle   {:name  "Team Turtle"
                              :cards #{"Aumakua" "Turtlebacks" "Shell Corporation" "Bullfrog"}}
                   :ungulate {:name  "Ungulate"
                              :cards #{"Celebrity Gift" "Trojan Horse" "Patron" "Green Level Clearance" "Improved Tracers"
                                       "Taurus"}
                              :nickname "whinnie"}
                   :bee      {:name  "Bee"
                              :cards #{"Plan B" "Honeyfarm" "Chrysalis" "Hive" "Hivemind" "Swarm" "Mutate" "Interrupt 0" "Special Report"}
                              :nickname "buzz buzz"}
                   :bird     {:name  "Bird"
                              :cards #{"Owl" "Firmware Updates"}}
                   :whale    {:name  "Whale"
                              :cards #{"Howler" "Darwin" "Swordsman"}}})

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

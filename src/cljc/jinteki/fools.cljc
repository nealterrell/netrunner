(ns jinteki.fools)

(def animal-teams {:dog      {:name     "Team Dog"
                              :cards    #{"Lycan" "Synth DNA Modification" "Rover Algorithm" "Watchdog" "Lab Dog"
                                          "Cerberus \"Lady\" H1" "Cerberus \"Rex\" H2" "Ceberus \"CUJ.0\" H3"
                                          "Pup" "Komainu" "News Hound" "Emergency Shutdown" "HQ Interface"}
                              :card-icon "🐶"
                              :nickname "bow-wow"}
                   :cat      {:name     "Team Cat"
                              :cards    #{"BlacKat" "Rumor Mill" "Marathon" "Hellion Beta Test" "Quantum Predictive Model"}
                              :card-icon "🐱"
                              :nickname "kitty meow"}
                   :snake    {:name     "Team Snake"
                              :cards    #{"Susanoo-no-Mikoto" "Cobra" "Viper" "Mamba" "Caduceus" "Puffer" "Lamprey" "Wormhole"}
                              :card-icon "🐍"
                              :nickname "snek"}
                   :lizard   {:name     "Team Lizard"
                              :cards    #{"Financial Collapse" "Shattered Remains" "Casting Call" "Chameleon" "Exploratory Romp" "Modded"
                                          "Maw"}
                              :card-icon "🦎"
                              :nickname "dinorawr"}
                   :turtle   {:name     "Team Turtle"
                              :cards    #{"Aumakua" "Turtlebacks" "Shell Corporation" "Bullfrog"}
                              :card-icon "🐢"
                              :nickname "cowabunga"
                              :sounds   {:play #{"turtle-play1"}
                                         :use  #{"turtle-use1"}}}
                   :ungulate {:name     "Team Ungulate"
                              :cards    #{"Celebrity Gift" "Trojan Horse" "Patron" "Green Level Clearance" "Improved Tracers"
                                          "Taurus" "On the Lam" "Battering Ram" "Wari"}
                              :card-icon "🐮"
                              :nickname "whinnie"
                              :sounds   {}}
                   :bee      {:name     "Team Bee"
                              :cards    #{"Plan B" "Honeyfarm" "Chrysalis" "Hive" "Hivemind" "Swarm" "Mutate" "Interrupt 0" "Special Report"
                                          "Bug"}
                              :card-icon "🐝"
                              :nickname "buzz buzz"}
                   :bird     {:name     "Team Bird"
                              :cards    #{"Owl" "Firmware Updates" "Kongamato" "Golden" "Peregrine" "Saker" "Recon Drone" "Origami"
                                          "GS Shrike M2" "Data Raven" "Peacock" "Egret"}
                              :card-icon "🐦"
                              :nickname "birdo"}
                   :whale    {:name     "Team Whale"
                              :cards    #{"Howler" "Darwin" "Swordsman" "Gbahali" "Red Herrings" "Spearfishing" "Leviathan"}
                              :card-icon "🐳"
                              :nickname ""
                              :sounds   {:trash #{"whale-trash"}
                                         :play  #{"whale-play1" "whale-play2"}}}})

(defn team-cards [team]
  (get-in animal-teams [team :cards]))

(defn team-nickname [team]
  (get-in animal-teams [team :nickname]))

(defn team-name [team]
  (get-in animal-teams [team :name]))

(defn card-team [card-title]
  (some #(when (contains? (:cards (second %)) card-title)
           (first %))
        animal-teams))

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

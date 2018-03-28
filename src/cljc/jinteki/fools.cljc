(ns jinteki.fools)

(def animal-teams {:dog      {:name  "Team Dog"
                              :cards #{"Lycan" "Synth DNA Modification" "Rover Algorithm" "Watchdog" "Lab Dog"
                                       "Cerberus \"Lady\" H1" "Cerberus \"Rex\" H2" "Ceberus \"CUJ.0\" H3"
                                       "Pup" "Komainu" "News Hound" "Emergency Shutdown" "HQ Interface"}
                              :nickname "bow-wow"}
                   :cat      {:name  "Team Cat"
                              :cards #{"BlacKat" "Rumor Mill" "Marathon" "Hellion Beta Test" "Quantum Predictive Model"}
                              :nickname "kitty meow"}
                   :snake    {:name  "Team Snake"
                              :cards #{"Susanoo-no-Mikoto" "Cobra" "Viper" "Mamba" "Caduceus" "Puffer" "Lamprey" "Wormhole"}
                              :nickname "snek"}
                   :lizard   {:name  "Team Lizard"
                              :cards #{"Financial Collapse" "Shattered Remains" "Casting Call" "Chameleon" "Exploratory Romp" "Modded"
                                       "Dinosaurus" "Test Run"}}
                   :turtle   {:name  "Team Turtle"
                              :cards #{"Aumakua" "Turtlebacks" "Shell Corporation" "Bullfrog"}}
                   :ungulate {:name  "Team Ungulate"
                              :cards #{"Celebrity Gift" "Trojan Horse" "Patron" "Green Level Clearance" "Improved Tracers"
                                       "Taurus" "Battering Ram"}
                              :nickname "whinnie"}
                   :bee      {:name  "Team Bee"
                              :cards #{"Plan B" "Honeyfarm" "Chrysalis" "Hive" "Hivemind" "Swarm" "Mutate" "Interrupt 0" "Special Report"
                                       "Bug"}
                              :nickname "buzz buzz"}
                   :bird     {:name  "Team Bird"
                              :cards #{"Owl" "Firmware Updates" "Kongamato" "Golden" "Peregrine" "Saker" "Recon Drone" "Origami"
                                       "GS Shrike M2" "Data Raven" "Peacock" "Egret"}}
                   :whale    {:name  "Team Whale"
                              :cards #{"Howler" "Darwin" "Swordsman" "Gbahali" "Red Herrings" "Spearfishing"}}})

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

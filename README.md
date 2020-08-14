# OOAD
To effectively prevent the wide spread of the Corona virus pandemic (COVID-19), tracking the status of the entire population in terms of the disease contraction 
and personal contacts is one of the essential but challenging methods. The most feasible solution for tracking the disease is to design and install a tracking application on smartphones. 
Such a virus tracking app (TrackApp for short) would ideally be installed on everyone’s phone.
Assumptions (maybe unrealistic):
• All privacy issues have been taken care of. So, this project needs NOT to consider such issues.
• All smartphones installed with TrackApp would turn on Bluetooth and communicate via  Bluetooth. Two essential phone facilities should be used: Bluetooth and Location tracking. 
Each phone carries a colored token marking the status of its user and initialized as below:
• Red – infected or test positive;
• Green – normal or test negative;
• Orange – having symptoms or had close contact with someone infected.
The token changes from green to orange or red according to the specified condition, from red to orange after the user is tested negative, and from orange to green after
the user self-quarantined for 2 weeks.
Two phones within a  distance of 5 meters or less would inform each other of their users’ disease status by sending each other its token and location.
A warning signal is displayed whenever:
• The phone is within a  distance of 5 meters of an infected person (a red token is received);
• The phone receives an orange token, or the location of the phone has a density between M and N persons per KM2.
An alarm signal is triggered, and its own token is set to orange whenever:
• The phone is within a  distance of 2 meters of an infected person (a red token is
received); or the location of the phone has a density higher than N persons per KM2.
The entire status including the locations within the past 7 days is sent to CDC
periodically every week, and as soon as the token becomes red.
The project must be able to demonstrate the scenarios of all TrackApp’s user’s 3 statuses,
plus the token turning red with transmission of the situation to CDC. TrackApp should allow its user to retrieve relevant disease and location data whenever desired.

A flexible command-line weather utility:


To Do:
* Initial setup for new users
  * Automatic Devkey generator 
    * (if this utility becomes popular -- forecast.io only allows 1000 API requests per day)
    * collect email address and password, pipe to https://developer.forecast.io/register 
  * Automatic coordinate collection / request closest city or zipcode to user, and use openmaps' API to get corresponding lat,long
  * Huge parameter flexability
    * Summary
    * Current vs Weekly
    * Ability to request humidity, barometric pressure, etc.

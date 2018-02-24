# Advanced Android module - MadridShops

This application shows information about shops and activities on a map or a list.
Information is downloaded from network and saved to a DB.
You also have a detail view with custom information.

## Main View
- Main View downloads activities and shops
- Main View shows buttons if download is ok
- Main View navigates to Activities or Shops

## Activities or Shops
The screen shows a map and a recyclerView
- Map
	- If you select a pin, you will show custom info
	- If you press a pin's marker, you'll navigate to detail view
- RecyclerView
	- If you tap on a cell, you'll navigate to detail view also

## Detail View
- Information
	- The intent has a parameter with the 'tag' information. This tag is an Activity or a Shop.
	- The intent information is showed first, but there is a task in background to check the information in the DB. When this information is available, the items are refreshed with new info.
- Hidden  Map
	- Detail view has a hidden map. At the bottom of the screen you can push to open or hide the image's map
## Toolbars
- Main View has no toolbar
- Main activities have a custom toolbar to test Picasa, and Home button
- Detail activities only Home button

## Downloads
The download check if cache exists (DB is empty). If there is no cache, it downloads form network, then save date to DB, then get date from DB (with true item ID), and finally go back with that list

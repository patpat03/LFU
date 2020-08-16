# LFU
Created a least frequently used cache which has a size parameter. After a certain sized number of elements have been added to the cache it will remove the 
least frequently used. If is a tie between two the most recently used will be removed. This is done through a LinkedHashSet which can easily keep track of least
frequently used values based on the position.

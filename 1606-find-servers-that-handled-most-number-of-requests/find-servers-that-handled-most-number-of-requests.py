from heapq import heappop, heappush
from sortedcontainers import SortedList


class Solution:

  def busiestServers(
      self, k: int, arrival: list[int], load: list[int]
  ) -> list[int]:
    # Available servers maintained in sorted order
    available = SortedList(range(k))
    # Min-heap storing: (release_time, server_id)
    busy = []
    # Count of handled requests per server
    count = [0] * k

    for i, (start, duration) in enumerate(zip(arrival, load)):
      # 1. Release all servers that are done by current arrival time
      while busy and busy[0][0] <= start:
        _, server_id = heappop(busy)
        available.add(server_id)

      if not available:
        continue  # All servers busy -> request dropped

      # 2. Binary search for server >= i % k
      target = i % k
      idx = available.bisect_left(target)

      # Wrap around if no server >= i % k is free
      if idx == len(available):
        idx = 0

      server = available.pop(idx)
      count[server] += 1
      heappush(busy, (start + duration, server))

    max_handled = max(count)
    return [s for s, c in enumerate(count) if c == max_handled]
    
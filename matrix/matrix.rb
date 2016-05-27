require 'set'

class Matrix
  attr_reader :cities

  def initialize city_count:, machine_count:, roads:, cities_with_machines:
    @cities= Set.new

    city_count.times do |i|
      @cities.add({
        id: i,
        links: Set.new,
        machine: (cities_with_machines.include?(i) ? true : false),
      })
    end

    roads.each do |road|
      city1_id= road[:between][0]
      city2_id= road[:between][1]
      time= road[:time]

      city1= @cities.select {|c| c[:id]==city1_id}[0]
      city2= @cities.select {|c| c[:id]==city2_id}[0]

      city1[:links].add({
        linked_city: city2,
        time: time,
        enabled: true,
      })

      city2[:links].add({
        linked_city: city1,
        time: time,
        enabled: true,
      })
    end
  end

  def cities_with_machines
    @cities_with_machines||= @cities.select {|c| c[:machine]==true}
  end

  def all_routes_between_cities_with_machines
    cities_with_machines_pairs= cities_with_machines.permutation(2)
    cities_with_machines_uniq_pairs= cities_with_machines_pairs.
        map {|cp| cp.sort {|c1,c2| c1[:id] <=> c2[:id] } }.
        uniq
        # .map {|cp| [cp[0][:id], cp[1][:id]]}.to_s

    all_routes= cities_with_machines_uniq_pairs.map {|city_pair| routes_between_cities(city_pair[0], city_pair[1])}
    all_routes.flatten!(1)

    # puts all_routes[0].map {|r| [r[:from], r[:to]]}.to_s
    # puts all_routes[1].map {|r| [r[:from], r[:to]]}.to_s
    # puts all_routes[2].map {|r| [r[:from], r[:to]]}.to_s
    # puts all_routes[3].map {|r| [r[:from], r[:to]]}.to_s
    # puts all_routes[4].map {|r| [r[:from], r[:to]]}.to_s
    # puts all_routes[5].map {|r| [r[:from], r[:to]]}.to_s
  end

  # def shortest_time_to_save_zeon
  #   all_routes= all_routes_between_cities_with_machines
  #   all_possible_roads= all_routes.flatten

  #   best_time= 9999999999999
  #   all_possible_roads.permutation.each do |roads|
  #     roads.map {|road| road[:]}
  #   end

  # end

  def routes_between_cities city1, city2
    routes= []
    path= [{
      from: city1[:id],
      to: city1[:id],
      time: 0,
      enabled: true,
    }]
    _next_road from: city1, looking_for: city2, path: path, routes: routes
    routes
  end

  def disable_road_between city1:, city2:
    city1_link_to_city2= link_between(city1, city2)
    raise "city already disabled" if (city1_link_to_city2[:enabled]== false)
    city1_link_to_city2[:enabled]= false

    city2_link_to_city1= link_between(city2, city1)
    raise "city already disabled" if (city2_link_to_city1[:enabled]== false)
    city2_link_to_city1[:enabled]= false
  end

  def enable_road_between city1:, city2:
    city1_link_to_city2= link_between(city1, city2)
    raise "city already enabled" if (city1_link_to_city2[:enabled]== true)
    city1_link_to_city2[:enabled]= true

    city2_link_to_city1= link_between(city2, city1)
    raise "city already enabled" if (city2_link_to_city1[:enabled]== true)
    city2_link_to_city1[:enabled]= true
  end

private

  def link_between city1, city2
    city1[:links].select {|lc| lc[:linked_city][:id]==city2[:id]}[0]
  end

  def _next_road from:, looking_for:, path: [], routes: []
    from[:links].each do |link|   #TODO: skip enabled= false here
      next if path.map {|road| [road[:from], road[:to]]}.flatten.include?(link[:linked_city][:id]) # skips `from` city if it is already in `path`

      path.push({
        from: path.last[:to],
        to: link[:linked_city][:id],
        time: link[:time],
        enabled: link[:enabled],
      })

      if (link[:linked_city][:id]==looking_for[:id])
        routes<< path.dup

        # puts "---------"
        # puts routes.count
        # routes.each do |route|
        #   puts route.map {|l| [l[:from], l[:to]]}.to_s
        # end
        # puts "---------"
      else
        _next_road from: link[:linked_city], looking_for: looking_for, path: path, routes: routes
      end

      path.pop()
    end
  end

end
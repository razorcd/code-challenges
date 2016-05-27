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

private

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

        puts "---------"
        puts routes.count
        routes.each do |route|
          puts route.map {|l| [l[:from], l[:to]]}.to_s
        end
        puts "---------"
      else
        _next_road from: link[:linked_city], looking_for: looking_for, path: path, routes: routes
      end

      path.pop()
    end
  end

end

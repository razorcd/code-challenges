require 'ostruct'

class Travel
  def initialize fill:, consume:
    create_linked_list_for fill: fill, consume: consume
  end

  def possible_starting_cities_count_if tank_capacity:
    count=0
    traverse do |current_link|
      count+=1 if is_a_possible_starting_city?(current_link, tank_capacity)
    end
    count
  end

private

  def create_linked_list_for fill:, consume:
    last_link= nil

    for i in (0..fill.length-1)
      new_link= OpenStruct.new({
        gallons_to_fill: fill[i],
        gallons_consumes_to_next_city: consume[i],
        next_link: nil,
      })
      @start_link= new_link unless @start_link
      last_link.next_link= new_link if last_link
      last_link= new_link
    end

    last_link.next_link= @start_link
  end

  def is_a_possible_starting_city? start, tank_capacity
    gallons_in_car= 0
    traverse(start) do |current_link|

      #fill
      gallons_in_car+= current_link.gallons_to_fill
      gallons_in_car= tank_capacity if gallons_in_car>tank_capacity

      #go_next_city
      gallons_in_car-= current_link.gallons_consumes_to_next_city

      return(false) if gallons_in_car<0
    end
    true
  end

  def traverse current_link= @start_link, stop_at= nil, &block
    stop_at= current_link unless stop_at
    yield current_link
    traverse(current_link.next_link, stop_at, &block) unless current_link.next_link==stop_at
  end
end

require 'ostruct'

class Travel
  def initialize cities:, gallons:
    create_linked_list_for cities: cities, gallons: gallons
  end

  def possible_starting_cities_count
    count=0
    traverse do |current_link|
      count+=1 if is_a_possible_starting_city?(current_link)
    end
    count
  end

private

  def create_linked_list_for cities:, gallons:
    last_link= nil

    for i in (0..cities.length-1)
      new_link= OpenStruct.new({
        gallons_needed_to_get_here: cities[i],
        gallons_to_fill: gallons[i],
        next_link: nil,
      })
      @start_link= new_link unless @start_link
      last_link.next_link= new_link if last_link
      last_link= new_link
    end

    last_link.next_link= @start_link
  end

  def is_a_possible_starting_city? start
    gallons_in_car= 0
    traverse(start) do |current_link|
      gallons_in_car+= current_link.gallons_to_fill
      return(false) if current_link.next_link.gallons_needed_to_get_here> gallons_in_car
    end
    true
  end

  def traverse current_link= @start_link, &block
    yield current_link
    traverse(current_link.next_link, &block) unless current_link.next_link==@start_link
  end
end

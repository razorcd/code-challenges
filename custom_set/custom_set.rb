class CustomSet

  def initialize
    @size = 0
    @elements = Array.new(size)
  end

  def is_empty?
    size == 0
  end

  def add element
    return if contains(element)

    add_memory
    @elements[size] = element
    @size += 1
    @empty = false
  end

  def size
    @size
  end

  def contains element
    index_of(element) >= 0
  end

  def remove element
    index = index_of(element)
    return unless index >= 0

    @elements[index] = @elements[size-1]
    @elements[size] = nil
    @size -= 1
  end

private

  def add_memory mem_size= 1
    new_elements= Array.new(size + mem_size)
    for i in (0..size-1)
      new_elements[i] = @elements[i]
    end
    @elements = new_elements
  end

  def index_of element
    for i in (0..size-1)
      return i if @elements[i] == element
    end
    return -1
  end

end

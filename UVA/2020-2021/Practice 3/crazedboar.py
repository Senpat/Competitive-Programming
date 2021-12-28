from decimal import *

n_trees = int(input())

trees = []

for i in range(n_trees):
    trees.append([Decimal(a) for a in input().split(' ')])

boar_radius, boar_dist = [Decimal(a) for a in input().split(' ')]
intervals = []

PI = math.pi

# treat bull as point
# add bull radius to trees
def circle_intersections(
    cx0,  cy0,  radius0,
    cx1,  cy1,  radius1):

    dx = cx0 - cx1
    dy = cy0 - cy1
    dist = (dx * dx + dy * dy) ** Decimal(.5)

    if dist > radius0 + radius1:
        return 0
    elif dist < abs(radius0 - radius1):
        return 0
    elif (dist == 0) and (radius0 == radius1):
        return 0
    else:
        a = (radius0 * radius0 -
            radius1 * radius1 + dist * dist) / (2 * dist)
        h = (radius0 * radius0 - a * a) ** Decimal(.5)

        cx2 = cx0 + a * (cx1 - cx0) / dist
        cy2 = cy0 + a * (cy1 - cy0) / dist

        intersection1 = ((cx2 + h * (cy1 - cy0) / dist), (cy2 - h * (cx1 - cx0) / dist))
        intersection2 = ((cx2 - h * (cy1 - cy0) / dist), (cy2 + h * (cx1 - cx0) / dist))

        if (dist == radius0 + radius1):
            return [intersection1]
        return [intersection1, intersection2]

def find_angle_to_point(x, y):
    degrees = Decimal(math.degrees(math.atan2(y,x)))
    # radians = (radians + Decimal(2*PI)) % Decimal(2*PI)
    degrees = (degrees + 360) % 360
    return degrees

def find_circle_tan_angle(tr, dist_to_tree):
    return Decimal(math.degrees(math.asin(tr/dist_to_tree)))

def dist(p1, p2):
    x1,y1 = p1
    x2,y2 = p2
    return Decimal(math.sqrt( (x2 - x1) ** Decimal(2) +  (y2 - y1) ** Decimal(2)))

for t in trees:
    tx, ty, tr = t
    tr += boar_radius

    pt = tx, ty
    pb = (0,0)

    dist_between_tree = dist(pb, pt)
    if dist_between_tree > boar_dist:
        # the tree is out of the circle
        if (dist_between_tree-boar_dist) > tr:
            # tree is out of reach
            continue

        # tree overlaps with outer edge
        intersections = circle_intersections(0,0,boar_dist, tx, ty, tr)
        assert len(intersections) == 2

        int1, int2 = intersections

        angle1 = find_angle_to_point(int1[0], int1[1])
        angle2 = find_angle_to_point(int2[0], int2[1])

        if abs(angle2-angle1) > 180:
            angle_range = (max(angle1, angle2), min(angle1, angle2))
            angle_range1 = (angle_range[0], Decimal(360))
            angle_range2 = (Decimal(0), angle_range[1])
            intervals.append(angle_range1)
            intervals.append(angle_range2)
        else:
            angle_range = (min(angle1, angle2), max(angle1, angle2))
            intervals.append(angle_range)
    else:
        # the tree is in the circle
        # print('In circle')
        anglec = find_angle_to_point(tx, ty)

        # print(anglec)

        angleAdd = find_circle_tan_angle(tr, dist_between_tree)
        # print(angleAdd)
        angle1 = anglec + angleAdd
        angle2 = anglec - angleAdd

        if abs(angle2-angle1) > 180:
            angle_range = (max(angle1, angle2), min(angle1, angle2))
            angle_range1 = (angle_range[0], Decimal(360))
            angle_range2 = (Decimal(0), angle_range[1])
            intervals.append(angle_range1)
            intervals.append(angle_range2)
        else:
            angle_range = (min(angle1, angle2), max(angle1, angle2))
            intervals.append(angle_range)


num_int = len(intervals)
intervals = sorted(intervals, key=lambda x: x[0])
# print(intervals)

if num_int == 0:
    print(1)
else:
    merged_intervals = [intervals[0]]

    for i in range(1, num_int):
        a1, e1 = merged_intervals.pop()
        a2, e2 = intervals[i]

        if e1 < a2:
            merged_intervals.append((a1, e1))
            merged_intervals.append((a2, e2))
        else:
            merged_intervals.append((min(a1,a2), max(e1,e2)))

    total_angle = Decimal(360)
    for i in merged_intervals:
        total_angle -= i[1] - i[0]

    # print(total_angle)

    print(total_angle/Decimal(360))

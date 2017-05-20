using System.Collections.Generic;

using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerContracts
{
    interface IOrderOperations
    {
        void Create(OrderDTO order, ICollection<OrderDetailsDTO> orderDetails);
        IEnumerable<OrderDTO> Get();
    }
}
